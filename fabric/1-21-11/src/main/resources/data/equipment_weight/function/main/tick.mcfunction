scoreboard players add #global weight_timer 1
execute if score #global weight_timer matches 100.. run function equipment_weight:main/every_five_seconds
execute if score #global weight_timer matches 7.. run function equipment_weight:training/training_bar
execute if score #global weight_timer matches 27.. run function equipment_weight:training/training_bar
execute if score #global weight_timer matches 47.. run function equipment_weight:training/training_bar
execute if score #global weight_timer matches 67.. run function equipment_weight:training/training_bar
execute if score #global weight_timer matches 87.. run function equipment_weight:training/training_bar
