from functools import reduce


def calculate_values(time, distance):
    for i in range(0, time+1):
        if (time - i) * i > distance:
            v1 = i
            break

    for i in reversed(range(0, time+1)):
        if (time - i) * i > distance:
            v2 = i
            break

    return v2-v1+1


def part1():
    time, distance = open("input").readlines()
    time = [int(x) for x in time.split()[1:]]
    distance = [int(x) for x in distance.split()[1:]]

    values = [calculate_values(t, d) for t, d in zip(time, distance)]

    print(reduce(lambda x, y: x*y, values))



def part2():
    time, distance = open("input").readlines()
    time = int("".join(x for x in time.split()[1:]))
    distance = int("".join(x for x in distance.split()[1:]))

    print(calculate_values(time, distance))


if __name__ == '__main__':
    part1()
    part2()
