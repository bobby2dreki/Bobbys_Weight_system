scoreboard players set #global weight_timer 0
execute as @a run function equipment_weight:main/strength_default
execute as @a run function equipment_weight:main/strength_player
execute as @a run function equipment_weight:weight/check_weight
execute as @a if score #global training matches 1 run function equipment_weight:training/training
execute as @a run function equipment_weight:weight/carry_effects