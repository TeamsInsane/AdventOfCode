from collections import defaultdict


def part1():
    score = 0
    with open("input") as file:
        for line in file:
            line = line.split(":")[1][1:].split("|")
            winning_numbers = line[0].split()
            numbers = line[1].split()
            numbers_count = sum(1 for number in numbers if number in winning_numbers)
            score += 2 ** (numbers_count - 1) if numbers_count > 0 else 0

    print(score)


def part2():
    card_number = 1
    scratchcard_instances = defaultdict(lambda: 1)
    for line in open("input"):
        line = line.split(":")[1][1:].split("|")
        winning_numbers = line[0].split()
        numbers = line[1].split()
        numbers_count = sum(1 for number in numbers if number in winning_numbers)
        for i in range(card_number+1, card_number+numbers_count+1):
            scratchcard_instances[i] += scratchcard_instances[card_number]
        card_number += 1

    # + 1 because of the last scratchcard not being in the scratchcard_instances
    print(sum(scratchcard_instances.values()) + 1)


if __name__ == '__main__':
    part1()
    part2()
