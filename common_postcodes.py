# List of your filenames - replace these with your actual file paths
folder_path = "./London_Postcode_Test_Files/"
filenames = [
    "1000_London_Postcodes.txt", 
    "2000_London_Postcodes.txt",        
    "4000_London_Postcodes.txt", 
    "8000_London_Postcodes.txt",
    "16000_London_Postcodes.txt"
]

# List to store sets of WC postcodes from each file
wc_postcode_sets = []

for filename in filenames:
    wc_postcodes = set()
    with open(folder_path+filename, 'r') as file:
        for line in file:
            # Clean the postcode and check if it starts with WC
            postcode = line.strip()
            if postcode.startswith('ZZ'):
                # print(f"Checking postcode: {postcode}")
                wc_postcodes.add(postcode)
    print(f"Found {len(wc_postcodes)} WC postcodes in {filename}")
    wc_postcode_sets.append(wc_postcodes)

# Find common postcodes across all files
common_postcodes = set.intersection(*wc_postcode_sets)

# Display results
print(f"Found {len(common_postcodes)} common WC postcodes:")
for postcode in sorted(common_postcodes):
    print(postcode)