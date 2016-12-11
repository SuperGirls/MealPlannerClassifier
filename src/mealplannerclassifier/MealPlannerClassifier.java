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
    
    public MealPlannerClassifier() throws JessException, IOException{
        rete = new Rete();
        context = rete.getGlobalContext();
        generateRules();
    }
    
    public void generateRules() throws FileNotFoundException, JessException, IOException{
        FileReader file = new FileReader("rules/mealplanner.clp");
             
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
        Iterator it = rete.listDeftemplates();
               
        rete.eval("rules");
        rete.eval("facts");
        Deftemplate template = (Deftemplate) it.next();
        if(!"MAIN::meals".equals(template.getName())){
            while (it.hasNext()) {
                template = (Deftemplate) it.next();
                if("MAIN::meals".equals(template.getName()))
                    break;
            }
        }
        
        for(int i=0; i<args.length; i++){
            if(!args[i].equals("None"))
                f.setSlotValue(template.getSlotName(i), new Value(args[i].toLowerCase(), RU.STRING));
        }
        
        rete.assertFact(f);
        rete.eval("(run)");
        
        it = rete.listFacts();
        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            if ("MAIN::result_choice".equals(fact.getName()))
               result = fact.getSlotValue("val").toString();
        }
        return result;
    }
    
}

