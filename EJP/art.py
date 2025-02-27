from PIL import Image
import numpy as np

# Load image and convert to grayscale
image = Image.open("T.jpg").convert("L")

# Resize while maintaining aspect ratio (accounting for font width-height ratio)
width, height = image.size
aspect_ratio = height / width
font_aspect_ratio = 2.0  # Adjust for terminal character proportions
new_width = 500  # Higher resolution for better detail
new_height = int((aspect_ratio * new_width) / font_aspect_ratio)

# Resize without Image.ANTIALIAS (as it is now default)
image = image.resize((new_width, new_height))

# Convert image to numpy array
pixels = np.array(image)

# Apply gamma correction for perceptual brightness improvement
gamma = 2.2
pixels = ((pixels / 255.0) ** (1/gamma)) * 255

# Define ASCII character set from dark to light (optimized for accuracy)
ascii_chars = "@#%*+=-:. "  # Adjust based on contrast needs

# Normalize pixel values to map them to ASCII indices
normalized_pixels = np.round((pixels / 255) * (len(ascii_chars) - 1)).astype(int)

# Generate ASCII art
ascii_art = "\n".join("".join(ascii_chars[pixel] for pixel in row) for row in normalized_pixels)

# Print and save the ASCII art
print(ascii_art)

with open("output_ascii.txt", "w") as f:
    f.write(ascii_art)
