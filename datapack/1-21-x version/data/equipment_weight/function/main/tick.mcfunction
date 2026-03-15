scoreboard players add #global weight_timer 1
execute if score #global weight_timer matches 100.. run function equipment_weight:main/every_five_seconds
execute as @a if score #global training matches 1 run function equipment_weight:training/bar