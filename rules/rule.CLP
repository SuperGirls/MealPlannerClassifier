(deftemplate meals (slot hungry) (slot hurry) (slot temperature) (slot allergy_chicken) (slot allergy_egg) (slot allergy_dairy) (slot diabetes))

(defrule rule1
	(meals(hungry "yes"))
	(meals(hurry "no"))
	(meals(allergy_egg "no"))
	=>
	(assert(breakfast fried_rice_with_egg))
	(printout t "Your breakfast menu today is fried rice with egg" crlf))

(defrule rule2
	(meals(hungry "yes"))
	(meals(hurry "no"))
	(meals(temperature "cold"))
	(meals(allergy_chicken "no"))
	=>
	(assert(breakfast rice_porridge_with_chicken))
	(printout t "Your breakfast menu today is rice porridge with chicken" crlf))

(defrule rule3
	(meals(hungry "no"))
	(meals(hurry "yes"))
	(meals(temperature "hot"))
	=>
	(assert(breakfast fruits))
	(printout t "Your breakfast menu today is fruits" crlf))

(defrule rule4
	(meals(hungry "no"))
	(meals(hurry "yes"))
	(meals(allergy_dairy "no"))
	=>
	(assert(breakfast cereal_oatmeal))
	(printout t "Your breakfast menu today is cereal/oatmeal" crlf))

(defrule rule5
	(meals(hungry "yes"))
	(meals(hurry "yes"))
	=>
	(assert(breakfast tuna_sandwich))
	(printout t "Your breakfast menu today is tuna sandwich" crlf))

(defrule rule6
	(meals(hungry "no"))
	(meals(hurry "no"))
	(meals(temperature "hot"))
	(meals(allergy_dairy "no"))
	(meals(allergy_egg "no"))
	(meals(diabetes "no"))
	=>
	(assert(breakfast pancake_ice_cream))
	(printout t "Your breakfast menu today is pancake with ice cream" crlf))

(defrule rule7
	(meals(hungry "no"))
	(meals(hurry "yes"))
	(meals(temperature "cold"))
	(meals(allergy_dairy "no"))
	(meals(diabetes "no"))
	=>
	(assert(breakfast hot_milk))
	(printout t "Your breakfast menu today is hot milk" crlf))

(defrule rule8
	(meals(hungry "no"))
	(meals(hurry "no"))
	(meals(allergy_egg "no"))
	=>
	(assert(breakfast omelette))
	(printout t "Your breakfast menu today is omelette" crlf))

(defrule rule9
	(meals(hungry "yes"))
	(meals(hurry "no"))
	(meals(allergy_dairy "yes"))
	=>
	(assert(breakfast tuna_sandwich))
	(printout t "Your breakfast menu today is tuna sandwich" crlf))

(defrule rule10
	(meals(hungry "yes"))
	(meals(hurry "yes"))
	(meals(temperature "cold"))
	(meals(allergy_egg "not"))
	=>
	(assert(breakfast omelette))
	(printout t "Your breakfast menu today is omelette" crlf))

(reset)