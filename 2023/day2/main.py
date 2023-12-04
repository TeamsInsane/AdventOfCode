from functools import reduce

bag = {"red": 12, "green": 13, "blue": 14}


def part1():
    score = 0
    for line in open("input"):
        game_number = line[5:line.index(":")]
        for game_set in line[line.index(":")+1:].split(";"):
            number_color_cube = [cube.split() for cube in game_set.split(",")]
            for value, color in number_color_cube:
                if bag[color] < int(value):
                    break
            else:
                continue
            break
        else:
            score += int(game_number)

    print(score)


def part2():
    score = 0
    for line in open("input"):
        min_bag = {"red": 0, "blue": 0, "green": 0}
        for game_set in line[line.index(":")+1:].split(";"):
            number_color_cube = [cube.split() for cube in game_set.split(",")]
            for value, color in number_color_cube:
                if int(value) > min_bag[color]:
                    min_bag[color] = int(value)

        score += reduce(lambda x, y: y*x, min_bag.values())

    print(score)


if __name__ == '__main__':
    part1()
    part2()
