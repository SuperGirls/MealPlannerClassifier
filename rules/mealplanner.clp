(deftemplate meals (slot hungry) (slot hurry) (slot temperature) (slot allergy_chicken) (slot allergy_egg) (slot allergy_dairy) (slot diabetes))

(defrule rule1
	(meals(blood_type "A"))
	=>
        (meals(animal_product "no"))

(defrule rule2
	(meals(blood_type "B"))
	=>
        (meals(animal_product "yes"))
        (meals(protein_product ["veg", "seafood"]))

(defrule rule3
	(meals(blood_type "B"))
	(meals(heart_disease "no"))
	=>
	(meals(dairy_product "normal"))

(defrule rule4
	(meals(blood_type "B"))
	(meals(heart_disease "yes"))
	=>
	(meals(dairy_product "no"))

(defrule rule5
	(meals(blood_type "AB"))
	=>
	(meals(animal_product "yes"))
        (meals(protein ["animal", "veg", "seafood"]))

(defrule rule6
	(meals(blood_type "AB"))
	(meals(heart_disease "no"))
	=>
	(meals(dairy_product "high"))

(defrule rule7
	(meals(blood_type "AB"))
	(meals(heart_disease "yes"))
	=>
	(meals(dairy_product "no"))

(defrule rule8
	(meals(blood_type "O"))
	=>
	(meals(animal_product "yes"))
        (meals(dairy_product "no"))
        (meals(protein ["veg, seafood"]))

(defrule rule9
	(meals(animal_product "no"))
	=>
        (meals(dairy_product "no"))
        (meals(protein ["veg"]))

(defrule ruleMenu1
	(meals(animal_product "no"))
	(meals(dairy_product "no"))
	(meals(protein ["veg"]))
	=>
	(assert(result_choice menu_1))

(defrule ruleMenu2
	(meals(dairy_product "high"))
	=>
	(assert(result_choice menu_2))

(defrule ruleMenu3
	(meals(dairy_product "no"))
	(meals(protein ["animal", "veg", "seafood"]))
	=>
	(assert(result_choice menu_3))

(defrule ruleMenu4
	(meals(dairy_product "no"))
	(meals(protein ["veg", "seafood"]))
        (meals(activity "high"))
        (meals(kidney_disease "yes"))
	=>
	(assert(result_choice menu_4))

(defrule ruleMenu5
	(meals(dairy_product "no"))
	(meals(protein ["veg", "seafood"]))
        (meals(activity "no"))
        (meals(kidney_disease "no"))
	=>
	(assert(result_choice menu_5))

(defrule ruleMenu6
	(meals(dairy_product "no"))
	(meals(protein ["veg", "seafood"]))
        (meals(activity "normal"))
	=>
	(assert(result_choice menu_6))

(defrule ruleMenu7
	(meals(dairy_product "no"))
	(meals(dairy_product "normal"))
	=>
	(assert(result_choice menu_7))
	
(reset)