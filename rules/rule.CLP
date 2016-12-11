(defrule rule1
	(hungry yes)
	(hurry no)
	(not (allergy egg))
	=>
	(assert(breakfast fried_rice_with_egg))
	(printout t "Your breakfast menu today is fried rice with egg" crlf))

(defrule rule2
	(hungry yes)
	(hurry no)
	(temperature cold)
	(not (allergy chicken))
	=>
	(assert(breakfast rice_porridge_with_chicken))
	(printout t "Your breakfast menu today is rice porridge with chicken" crlf))

(defrule rule3
	(hungry no)
	(hurry yes)
	(temperature hot)
	=>
	(assert(breakfast fruits))
	(printout t "Your breakfast menu today is fruits" crlf))

(defrule rule4
	(hungry no)
	(hurry yes)
	(not (allergy dairy))
	=>
	(assert(breakfast cereal_oatmeal))
	(printout t "Your breakfast menu today is cereal/oatmeal" crlf))

(defrule rule5
	(hungry yes)
	(hurry yes)
	=>
	(assert(breakfast tuna_sandwich))
	(printout t "Your breakfast menu today is tuna sandwich" crlf))

(defrule rule6
	(hungry no)
	(hurry no)
	(temperature hot)
	(not (allergy dairy))
	(not (allergy egg))
	(diabetes no)
	=>
	(assert(breakfast pancake_ice_cream))
	(printout t "Your breakfast menu today is pancake with ice cream" crlf))

(defrule rule7
	(hungry no)
	(hurry yes)
	(temperature cold)
	(not (allergy dairy))
	(diabetes no)
	=>
	(assert(breakfast hot_milk))
	(printout t "Your breakfast menu today is hot milk" crlf))

(defrule rule8
	(hungry no)
	(hurry no)
	(not (allergy egg))
	=>
	(assert(breakfast omelette))
	(printout t "Your breakfast menu today is omelette" crlf))

(defrule rule9
	(hungry yes)
	(hurry no)
	(allergy dairy)
	=>
	(assert(breakfast tuna_sandwich))
	(printout t "Your breakfast menu today is tuna sandwich" crlf))

(defrule rule10
	(hungry yes)
	(hurry yes)
	(temperature cold)
	(not (allergy egg))
	=>
	(assert(breakfast omelette))
	(printout t "Your breakfast menu today is omelette" crlf))
