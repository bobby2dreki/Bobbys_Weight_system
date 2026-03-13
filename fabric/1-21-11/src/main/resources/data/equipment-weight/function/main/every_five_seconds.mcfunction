scoreboard players set #global weight_timer 0
execute as @a run function equipment-weight:main/strength_default
execute as @a run function equipment-weight:main/strength_player
execute as @a run function equipment-weight:main/check_weight
execute as @a run function equipment-weight:main/carry_effects