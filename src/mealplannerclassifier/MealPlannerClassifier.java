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
import jess.*;
import java.util.Scanner;
public class MealPlannerClassifier {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, JessException, IOException {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        System.out.println("---- Welcome To Meal Planner Classifier ----");
        Rete engine = new Rete();

        FileReader file = new FileReader("rules/rule.clp");
        Context context = engine.getGlobalContext();
        
        Rete rete = new Rete();
        
        try {
            //Parse file and add rules
            Jesp parser = new Jesp(file, engine);
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
            
            Fact f = new Fact("meals", rete);
            
            System.out.println("Hungry? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("hungry", new Value(input, RU.STRING));
            
            System.out.println("Hurry? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("hurry", new Value(input, RU.STRING));
            
            System.out.println("Temperature? hot/cold");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("temperature", new Value(input, RU.STRING));
            
            System.out.println("Allergy Chicken? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("allergy_chicken", new Value(input, RU.STRING));
            
            System.out.println("Allergy Egg? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("allergy_egg", new Value(input, RU.STRING));
            
            System.out.println("Allergy Dairy? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("allergy_dairy", new Value(input, RU.STRING));
            
            System.out.println("Diabetes? yes/no");
            input= scanner.nextLine();
            if(!input.equals(""))
                f.setSlotValue("diabetes", new Value(input, RU.STRING));
            
            rete.assertFact(f);
            
            rete.eval("(rules)");
            System.out.println();
            rete.eval("(facts)");
            System.out.println();
            rete.eval("(run)");
            
        } finally {
          file.close();
        }

    }
    
}

