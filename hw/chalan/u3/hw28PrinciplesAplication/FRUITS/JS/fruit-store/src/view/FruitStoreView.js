import FruitController from '../controller/FruitController.js'
import ConsoleUtil from '../utils/ConsoleUtil.js'
import ManageStockView from './ManageStockView.js'

class FruitStoreView {
  constructor() {
    this.controller = new FruitController()
  }

  async start() {
    await this.controller.init()

    while (true) {
      ConsoleUtil.title('FRUIT STORE')
      const fruits = await this.controller.getFruitNames()
      fruits.forEach((f, i) => console.log(`${i + 1}. ${f}`))
      ConsoleUtil.line()
      console.log('1. Buy Fruit')
      console.log('2. Manage Stock')
      console.log('0. Exit')

      const option = ConsoleUtil.input('Select option: ')

      if (option === '1') await this.buy(fruits)
      if (option === '2') return new ManageStockView().start()
      if (option === '0') process.exit()
    }
  }

  async buy(fruits) {
    const index = Number(ConsoleUtil.input('Fruit number: ')) - 1
    const quantity = Number(ConsoleUtil.input('Quantity: '))
    const total = await this.controller.buyFruit(fruits[index], quantity)

    if (total === -1) console.log('Fruit not found')
    else if (total === -2) console.log('Not enough stock')
    else console.log(`Total: $${total}`)
  }
}

export default FruitStoreView
