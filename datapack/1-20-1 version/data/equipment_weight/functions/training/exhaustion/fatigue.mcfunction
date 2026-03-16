execute if score @s exhaustion matches 1000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 2000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 3000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 4000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 5000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 6000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 7000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 8000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 9000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 10000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 11000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 12000.. run scoreboard players add @s fatigue 1
execute if score @s exhaustion matches 13000.. run scoreboard players add @s fatigue 1

# reduced progression
execute if score @s fatigue >= #global fatigue_threshold run scoreboard players add @s fatigue_threshold 1
execute if score @s fatigue >= #global fatigue_threshold run scoreboard players add @s fatigue_message 1
execute if score @s fatigue >= #global fatigue_threshold run scoreboard players set @s fatigue 0
scoreboard players operation @s training_progress -= @s fatigue_threshold

# fatigue debuff
execute if score @s fatigue_threshold matches 4 run effect give @s minecraft:slowness 6 0 true
execute if score @s fatigue_threshold matches 5 run effect give @s minecraft:slowness 6 1 true

# exhaustion
execute if score @s fatigue_message matches 1 run function equipment_weight:training/exhaustion/message
execute if score @s fatigue_message matches 3 run function equipment_weight:training/exhaustion/message
execute if score @s fatigue_message matches 5 run function equipment_weight:training/exhaustion/message
execute if score @s fatigue_message matches 7 run function equipment_weight:training/exhaustion/message
execute if score @s fatigue_message matches 9 run function equipment_weight:training/exhaustion/message
