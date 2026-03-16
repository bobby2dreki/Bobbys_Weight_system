execute unless score @s exhaustion matches 0.. run scoreboard players set @s exhaustion 0
execute if score @s weight_ratio matches ..3 run scoreboard players remove @s exhaustion 3
execute if score @s weight_ratio matches 4..6 run scoreboard players remove @s exhaustion 2
execute if score @s weight_ratio matches 7..9 run scoreboard players remove @s exhaustion 1
execute if score @s weight_ratio matches 11..13 run scoreboard players add @s exhaustion 1
execute if score @s weight_ratio matches 14..16 run scoreboard players add @s exhaustion 3
execute if score @s weight_ratio matches 17..20 run scoreboard players add @s exhaustion 5

function equipment_weight:training/exhaustion/awake
function equipment_weight:training/exhaustion/fatigue