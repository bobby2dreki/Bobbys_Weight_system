scoreboard players add @s fatigue_message 1
execute if score @s fatigue_threshold matches 1 run tellraw @s {"text":"You feel a little tired","color":"yellow"}
execute if score @s fatigue_threshold matches 2 run tellraw @s {"text":"You feel exhausted. Training is becoming harder.","color":"gold"}
execute if score @s fatigue_threshold matches 3 run tellraw @s {"text":"You are extremely tired. Your strength is fading.","color":"red"}
execute if score @s fatigue_threshold matches 4 run tellraw @s {"text":"You are utterly exhausted. You must sleep.","color":"dark_red"}
execute if score @s fatigue_threshold matches 5 run tellraw @s {"text":"You are on the verge of collapse.","color":"dark_red"}