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

import jess.*;
import java.util.Scanner;
public class MealPlannerClassifier {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String option;
        
        System.out.println("---- Welcome To Meal Planner Classifier ----");
        
        do {
            try {
                Rete r = new Rete();
                r.eval("(deftemplate point \"A 2D point\" (slot x) (slot y))");

                Fact f = new Fact("point", r);
                f.setSlotValue("x", new Value(37, RU.INTEGER));
                f.setSlotValue("y", new Value(49, RU.INTEGER));
                r.assertFact(f);

                r.eval("(facts)");
                r.eval("(defrule myrule (A) => (printout t \"A\" crlf))");
                Defrule dr = (Defrule) r.findDefrule("myrule");
                System.out.println(new PrettyPrinter(dr));
                
            } catch (JessException ex) {
                System.err.println(ex);
            }
            
            
            System.out.print("> Repeat?(y/n): ");
            option = input.nextLine();
        } while (option.equals("y"));
        
        System.out.println("... bye!");
    }
    
}

