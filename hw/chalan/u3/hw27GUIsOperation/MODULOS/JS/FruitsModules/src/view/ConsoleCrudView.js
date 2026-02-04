import CrudController from '../controller/CrudController.js'
import ConsoleUtil from '../utils/ConsoleUtil.js'
import domain from '../config/domain.js'

class ConsoleCrudView {
  constructor() {
    this.controller = new CrudController()
  }

  async start() {
    await this.controller.init(domain.collection)

    while (true) {
      ConsoleUtil.title(domain.title)

      const items = await this.controller.getAll()
      const mainField = domain.fields[0].name

      items.forEach((item, i) => {
        const name = item.name ?? item[mainField]
        const price = item.price ?? 0
        const stock = item.stock ?? 0
        console.log(`${i + 1}. ${name} | $${price} | Stock: ${stock}`)
      })

      ConsoleUtil.line()
      console.log('1. Add')
      console.log('2. Delete')
      console.log('3. Buy')

      const option = ConsoleUtil.input('Select option: ')

      if (option === '1') await this.add()
      if (option === '2') await this.remove()
      if (option === '3') await this.buy()
    }
  }

  async add() {
    const data = {}

    for (const field of domain.fields) {
      data[field.name] = ConsoleUtil.inputValidated(field.name, field)
    }

    await this.controller.add(data)
    console.log('Saved')
  }

  async remove() {
    const mainField = domain.fields[0].name
    const value = ConsoleUtil.inputValidated(mainField, domain.fields[0])

    await this.controller.removeByName(value)
    console.log('Deleted')
  }

  async buy() {
    const name = ConsoleUtil.input('Product name: ')
    const quantity = Number(ConsoleUtil.input('Quantity to buy: '))

    if (isNaN(quantity) || quantity <= 0) {
      console.log('Invalid quantity')
      return
    }

    try {
      const total = await this.controller.buy(name, quantity)

      ConsoleUtil.line()
      console.log(`TOTAL TO PAY: $${total.toFixed(2)}`)
      ConsoleUtil.line()
    } catch (error) {
      console.log(error.message)
    }
  }
}

export default ConsoleCrudView
