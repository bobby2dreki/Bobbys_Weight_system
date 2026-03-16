scoreboard players set @s training_gain 0
# threshold = strength * 10 by default
scoreboard players operation @s training_threshold = @s strength
scoreboard players operation @s training_threshold *= #global training_threshold

# gain depends on weight ratio
execute if score @s weight_ratio matches 10..12 run function equipment_weight:training/weight/light
execute if score @s weight_ratio matches 13..15 run function equipment_weight:training/weight/normal
execute if score @s weight_ratio matches 16..19 run function equipment_weight:training/weight/heavy

# too heavy → injury
execute if score @s weight_ratio matches 20.. run function equipment_weight:training/weight/injury

# normal training → add progress
scoreboard players operation @s training_progress += @s training_gain
scoreboard players operation @s training_ratio = @s training_progress
scoreboard players operation @s training_ratio *= #w100 weight_multi
scoreboard players operation @s training_ratio /= @s training_threshold

# strength increase
execute if score @s training_progress >= @s training_threshold run function equipment_weight:training/strength_increase


