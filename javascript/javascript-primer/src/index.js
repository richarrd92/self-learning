export function calculateTotal (items, tax) {
  // TODO

  // Ensure tax is positive
  tax = Math.abs(tax);
  
  let total = 0;

  for (const item of items) {
    if (item.taxable) {
      total += item.price + item.price * tax; // add price plus tax
    } else {
      total += item.price; // non-taxable
    }
  }

  return total;
}
