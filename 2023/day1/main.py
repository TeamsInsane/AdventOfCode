import re


def part1():
    with open("input") as file:
        print(sum([int(value[0] + value[-1]) for line in file.readlines() if (value := re.findall(r"\d", line))]))


# Part 2
def part2():
    rep = {"one": "o1e", "two": "t2o", "three": "t3e", "four": "f4r", "five": "f5e", "six": "s6x", "seven": "s7n", "eight": "e8t", "nine": "n9e"}
    rep = dict((re.escape(k), v) for k, v in rep.items())
    pattern = re.compile("|".join(rep.keys()))

    with open("input") as file:
        values = [pattern.sub(lambda m: rep[re.escape(m.group(0))], line) for line in file.readlines()]

    final_values = [pattern.sub(lambda m: rep[re.escape(m.group(0))], value) for value in values]
    print(sum([int(value[0] + value[-1]) for line in final_values if (value := re.findall(r"\d", line))]))


if __name__ == '__main__':
    part1()
    part2()
