/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mealplannerclassifier;

/**
 *
 * @author chairuniaulianusapati
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import jess.*;
import java.util.Scanner;
public class MealPlannerClassifier {
    
    private Rete rete;
    private Context context;
    
    public MealPlannerClassifier(){
        rete = new Rete();
        context = rete.getGlobalContext();
    }
    
    public void generateRules() throws FileNotFoundException, JessException, IOException{
        FileReader file = new FileReader("rules/rule.clp");
             
        try {
            //Parse file and add rules
            Jesp parser = new Jesp(file, rete);
            Object result = Funcall.TRUE;
            while (!result.equals(Funcall.EOF)) {
                result = parser.parseExpression(context, false);
                if (result instanceof HasLHS) {
                    HasLHS rule = (HasLHS) result;
                    rete.addDefrule(rule);
                 }
                if(result instanceof Deftemplate){
                    Deftemplate template = (Deftemplate) result;
                    System.out.println(template.toString());
                    rete.addDeftemplate(template);
                }
              
            }
        } finally {
          file.close();
        }
    }
    
    public String classify(String[] args) throws JessException{
        String result = new String();
        Fact f = new Fact("meals", rete);
        for(int i=0; i<args.length; i++){
            f.setSlotValue("hungry", new Value(args[i], RU.STRING));
        }
        
        rete.eval("(run)");
        
        Iterator it = rete.listFacts();
        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            if ("MAIN::breakfast".equals(fact.getName()))
               result = fact.getSlotValue("menu").toString();
       }
       return result;
    }
    
}

