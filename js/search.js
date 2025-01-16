// Get references to DOM elements
const searchBox = document.getElementById('search-box');
const searchButton = document.getElementById('search-button');
const productCards = document.querySelectorAll('.product-card');

// Function to filter products
function filterProducts() {
  const query = searchBox.value.toLowerCase();

  // Loop through all product cards and hide/show based on match
  productCards.forEach(card => {
    const name = card.dataset.name.toLowerCase();
    const description = card.dataset.description.toLowerCase();

    // Check if the search query matches the product name or description
    if (name.includes(query) || description.includes(query)) {
      card.style.visibility = 'visible';
      card.style.opacity = '1';
      card.style.height = 'auto';
      card.style.margin = '16px'; // Reset margin if hidden items affected it
    } else {
      card.style.visibility = 'hidden';
      card.style.opacity = '0';
      card.style.height = '0';
      card.style.margin = '0'; // Prevent spacing issues
    }
  });
}

// Attach event listeners for search functionality
searchButton.addEventListener('click', filterProducts);
searchBox.addEventListener('keypress', event => {
  if (event.key === 'Enter') {
    filterProducts();
  }
});

// Trigger search dynamically as the user types or deletes characters
searchBox.addEventListener('input', filterProducts);
