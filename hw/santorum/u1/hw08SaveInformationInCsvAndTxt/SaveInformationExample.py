
data = [
    [1, "Alice", 25],
    [2, "Bob", 30],
    [3, "Charlie", 35]
]


with open('output.csv', 'w') as file:
    file.write("ID,Name,Age\n")  
    for row in data:
        file.write(f"{row[0]},{row[1]},{row[2]}\n")


with open('output.txt', 'w') as file:
    file.write("ID\tName\tAge\n")  
    for row in data:
        file.write(f"{row[0]}\t{row[1]}\t{row[2]}\n")
