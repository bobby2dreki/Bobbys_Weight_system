scoreboard players set @s weight_ratio 0
scoreboard players operation @s weight_ratio += @s weight_total

# Multiply by 10 for precision
scoreboard players operation @s weight_ratio *= #w10 weight_multi

# Divide by player strength
scoreboard players operation @s weight_ratio /= @s strength_player
scoreboard players operation @s weight_ratio -= #w1 weight_multi

# Tier 1: Slightly Heavy
execute if score @s weight_ratio matches 12..15 run effect give @s minecraft:slowness 6 0 true
execute if score @s weight_ratio matches 12..15 run title @s actionbar {"text": "Slightly Heavy","color":"gray"}

# Tier 2: Heavy but still fine
execute if score @s weight_ratio matches 16..19 run effect give @s minecraft:slowness 6 1 true
execute if score @s weight_ratio matches 16..19 run title @s actionbar {"text": "Heavy, but manageable","color":"gray"}

# Tier 3: Heavy
execute if score @s weight_ratio matches 20..23 run effect give @s minecraft:slowness 6 2 true
execute if score @s weight_ratio matches 20..23 run title @s actionbar {"text": "Heavy!","color":"gray"}

# Tier 4: Now it's really to much
execute if score @s weight_ratio matches 24..30 run effect give @s minecraft:slowness 6 2 true
execute if score @s weight_ratio matches 24..30 run effect give @s minecraft:mining_fatigue 6 0 true
execute if score @s weight_ratio matches 24..30 run effect give @s minecraft:weakness 6 0 true
execute if score @s weight_ratio matches 24..30 run title @s actionbar {"text": "Now it's really to much","color":"gray"}

# Tier 5: Maybe, just maybe you should drop some load?
execute if score @s weight_ratio matches 31..150 run effect give @s minecraft:slowness 6 3 true
execute if score @s weight_ratio matches 31..150 run effect give @s minecraft:mining_fatigue 6 1 true
execute if score @s weight_ratio matches 31..150 run effect give @s minecraft:weakness 6 1 true
execute if score @s weight_ratio matches 31..150 run effect give @s minecraft:hunger 6 0 true
execute if score @s weight_ratio matches 31..150 run title @s actionbar {"text": "Maybe, just maybe you should drop some load?","color":"gray"}

# Tier 6: ✈ You thought you could carry a plane?
execute if score @s weight_ratio matches 151.. run effect give @s minecraft:slowness 6 5 true
execute if score @s weight_ratio matches 151.. run effect give @s minecraft:mining_fatigue 6 2 true
execute if score @s weight_ratio matches 151.. run effect give @s minecraft:weakness 6 2 true
execute if score @s weight_ratio matches 151.. run effect give @s minecraft:hunger 6 1 true
execute if score @s weight_ratio matches 151.. run title @s actionbar {"text":"However you got in this position, good luck moving","color":"red","bold":true}

# a lot of strength but no weight
execute if score @s weight_ratio matches ..2 run effect give @s minecraft:speed 6 0 true
execute if score @s weight_ratio matches ..2 run effect give @s minecraft:jump_boost 6 0 true
execute if score @s weight_ratio matches ..2 run effect give @s minecraft:haste 6 0 true
execute if score @s weight_ratio matches ..2 run effect give @s minecraft:strength 6 0 true