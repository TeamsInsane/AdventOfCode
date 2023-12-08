import re


def get_numbers_symbols():
    numbers = {y: {(value.span()[0], value.span()[1] - 1): value.group() for value in re.finditer(r"\d+", line)}
               for y, line in enumerate(open("input"))}
    symbols = {y: {value.span()[0] for value in re.finditer(r"[+/=#%@\-$*&]", line)}
               for y, line in enumerate(open("input"))}

    return numbers, symbols


def part1():
    numbers, symbols = get_numbers_symbols()
    max_y, max_x = 139, 139

    score = 0
    for y, values in symbols.items():
        for range_value in values:
            for range_y in [max(y-1, 0), y, min(y+1, max_y)]:
                for range_x in [max(range_value-1, 0), range_value, min(range_value+1, max_x)]:
                    for indexes, value in numbers[range_y].items():
                        if indexes[0] <= range_x <= indexes[1]:
                            score += int(value)
                            numbers[range_y][indexes] = 0

    print(score)


def part2():
    numbers, symbols = get_numbers_symbols()
    max_y, max_x = 139, 139

    score = 0
    for y, values in symbols.items():
        for range_value in values:
            part_numbers = []
            for range_y in [max(y - 1, 0), y, min(y + 1, max_y)]:
                for range_x in [max(range_value - 1, 0), range_value, min(range_value + 1, max_x)]:
                    for indexes, value in numbers[range_y].items():
                        if indexes[0] <= range_x <= indexes[1]:
                            if value != 0:
                                part_numbers.append(int(value))
                            numbers[range_y][indexes] = 0

            if len(part_numbers) == 2:
                score += part_numbers[0]*part_numbers[1]

    print(score)


if __name__ == '__main__':
    part1()
    part2()
