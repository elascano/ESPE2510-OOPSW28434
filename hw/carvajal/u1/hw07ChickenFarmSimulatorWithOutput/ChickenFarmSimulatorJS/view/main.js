import readlineSync from 'readline-sync';
import Chicken from '../model/Chicken.js';

function showMenu() {
  console.log('\n--- Chicken Farm Menu ---');
  console.log('1. View chicken characteristics');
  console.log('2. Make a chicken do its routine');
  console.log('3. Exit');
}

function selectChicken(chickens) {
  console.log('\nWhich chicken do you want to select?');
  chickens.forEach((chicken, index) => {
    console.log(`${index + 1}. ${chicken.name}`);
  });
  const choice = readlineSync.questionInt('Enter the number of the chicken: ');
  return chickens[choice - 1];
}

function main() {
  console.log('Welcome to the Chicken Farm Simulator');

  const chickens = [
    new Chicken(1, 'Lucy', 'White'),
    new Chicken(2, 'Clara', 'Brown'),
    new Chicken(3, 'Maria', 'Black')
  ];

  let option;
  do {
    showMenu();
    option = readlineSync.question('Choose an option (1-3): ');

    switch (option) {
      case '1': {
        const chicken = selectChicken(chickens);
        chicken.showData();
        break;
      }
      case '2': {
        const chicken = selectChicken(chickens);
        chicken.doRoutine();
        break;
      }
      case '3':
        console.log('Goodbye! 🐔');
        break;
      default:
        console.log('Invalid option. Please try again.');
    }
  } while (option !== '3');
}

main();