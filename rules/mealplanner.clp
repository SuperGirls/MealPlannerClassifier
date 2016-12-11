(deftemplate meals (slot blood_type) (slot activity) (slot heart_disease) (slot kidney_disease))

(deftemplate animal_product (slot value))
(deftemplate dairy_product (slot value))
(deftemplate protein (multislot value))
(deftemplate result_choice (slot val))

(defrule rule1
	(meals(blood_type "a"))
	=>
    (assert(animal_product(value "no"))))

(defrule rule2
	(meals(blood_type "b"))
	=>
    (assert(animal_product(value "yes")))
    (assert(protein(value "veg" "seafood"))))

(defrule rule3
	(meals(blood_type "b"))
	(meals(heart_disease "no"))
	=>
	(assert(dairy_product(value "normal"))))

(defrule rule4
	(meals(blood_type "b"))
	(meals(heart_disease "yes"))
	=>
	(assert(dairy_product(value "no"))))

(defrule rule5
	(meals(blood_type "ab"))
	=>
	(assert(animal_product(value "yes")))
    (assert(protein(value "animal" "veg" "seafood"))))

(defrule rule6
	(meals(blood_type "ab"))
	(meals(heart_disease "no"))
	=>
	(assert(dairy_product(value "high"))))

(defrule rule7
	(meals(blood_type "ab"))
	(meals(heart_disease "yes"))
	=>
	(assert(dairy_product(value "no"))))

(defrule rule8
	(meals(blood_type "o"))
	=>
	(assert(animal_product(value "yes")))
    (assert(dairy_product(value "no")))
    (assert(protein(value "veg" "seafood"))))

(defrule rule9
	(animal_product(value "no"))
	=>
    (assert(dairy_product(value "no")))
    (assert(protein(value "veg"))))

(defrule ruleMenu1
	(animal_product(value "no"))
	(dairy_product(value "no"))
	(protein(value "veg"))
	=>
	(assert(result_choice (val menu_1))))

(defrule ruleMenu2
	(dairy_product(value "high"))
	=>
	(assert(result_choice (val menu_2))))

(defrule ruleMenu3
	(dairy_product(value "no"))
	(protein(value "animal" "veg" "seafood"))
	=>
	(assert(result_choice (val menu_3))))

(defrule ruleMenu4
	(dairy_product(value "no"))
	(protein(value "veg" "seafood"))
    (meals(activity "high"))
    (meals(kidney_disease "yes"))
	=>
	(assert(result_choice (val menu_4))))

(defrule ruleMenu5
	(dairy_product(value "no"))
	(protein(value "veg" "seafood"))
    (meals(activity "high"))
    (meals(kidney_disease "no"))
	=>
	(assert(result_choice (val menu_5))))

(defrule ruleMenu6
	(dairy_product(value "no"))
	(protein(value "veg" "seafood"))
    (meals(activity "normal"))
	=>
	(assert(result_choice (val menu_6))))

(defrule ruleMenu7
	(animal_product(value "yes"))
	(dairy_product(value "normal"))
	=>
	(assert(result_choice (val menu_7))))
	
(reset)