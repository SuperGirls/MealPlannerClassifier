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

public class MealPlannerClassifier {
    public static Menu[] menus = {
        new Menu(1,"A vegan menu that is animal product free, including milk and butter free.","Coffee and peanut butter sandwich","Rice with kale and tempe","Corn, tomato, and red beans"),
        new Menu(2,"Menu with a composition that is high in dairy based components but no caffein.", "Fruits and yoghurt", "Spaghetti carbonara and cream spinach", "Kefir and fish and chips"),
        new Menu(3,"Menu that is high in protein but contains no dairy product.", "Chicken and carrot soup", "Chicken and quail egg soto soup with rice", "Kale with a small amount of shrimp"),
        new Menu(4,"<html>High calorie menu that contains no chicken, meat, potato and any dairy product. <br>This menu gets its protein portion from seafood and vegetable protein sources.</html>", "Red bean soup", "Red potatoes and red beans", "Rice with squid or fish"),
        new Menu(5,"<html>High calorie menu that contains no chicken, meat and any dairy product.<br>This menu gets its protein portion from seafood and vegetable protein sources.</html>", "Potatoes and mushroom", "Rice with red beans", "Rice with squid or fish"),
        new Menu(6,"Low calorie menu that contains no chicken, meat and dairy product, and is low in fat.","Fish and fruits","Red potatoes and shrimp","Katuk leaf and fruits"),
        new Menu(7,"<html>Menu that contains no chicken and meat.<br>This menu gets its protein portion from seafood and vegetable protein sources.</html>","Cereal and milk","Rice with shrimp","Katuk leaf and potatoes"),
        new Menu(8,"<html>Menu that contains no chicken and meat. <br>This menu contains a balanced portion of carbohydrate and fat.</html>","Indonesian steamed mackerel rice","Red potatoes and shrimp","Katuk leaf and fruits"),       
    };
    
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
        rete.eval("(reset)");
        return result;
    }  
}

class Menu{ 
    public int code;
    public String description;
    public String breakfast;
    public String lunch;
    public String dinner;
    
    public Menu(int c, String desc, String b, String l, String d){
        code = c;
        description = desc;
        breakfast = b;
        lunch = l;
        dinner = d;
    }
}

