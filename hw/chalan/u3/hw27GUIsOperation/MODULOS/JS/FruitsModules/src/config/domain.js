export default {
  title: 'Fruit Store',
  collection: 'fruits',
  fields: [
    {
      name: 'name',
      type: 'string',
      required: true,
      pattern: /^[A-Za-z\s]+$/
    },
    {
      name: 'price',
      type: 'number',
      required: true
    },
    {
      name: 'stock',
      type: 'int',
      required: true
    }
  ]
}
