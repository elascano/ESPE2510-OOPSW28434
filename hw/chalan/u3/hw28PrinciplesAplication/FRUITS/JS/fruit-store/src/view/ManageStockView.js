import FruitController from '../controller/FruitController.js'
import ConsoleUtil from '../utils/ConsoleUtil.js'
import FruitStoreView from './FruitStoreView.js'

class ManageStockView {
  constructor() {
    this.controller = new FruitController()
  }

  async start() {
    await this.controller.init()

    while (true) {
      ConsoleUtil.title('MANAGE STOCK')
      console.log('1. Add Fruit')
      console.log('2. Delete Fruit')
      console.log('0. Back')

      const option = ConsoleUtil.input('Select option: ')

      if (option === '1') await this.add()
      if (option === '2') await this.remove()
      if (option === '0') return new FruitStoreView().start()
    }
  }

  async add() {
    const name = ConsoleUtil.input('Name: ')
    const price = Number(ConsoleUtil.input('Price: '))
    const stock = Number(ConsoleUtil.input('Stock: '))

    if (await this.controller.existsFruit(name)) {
      console.log('Fruit already exists')
      return
    }

    await this.controller.addFruit(name, price, stock)
    console.log('Fruit added')
  }

  async remove() {
    const name = ConsoleUtil.input('Name to delete: ')
    await this.controller.deleteFruit(name)
    console.log('Fruit deleted')
  }
}

export default ManageStockView
