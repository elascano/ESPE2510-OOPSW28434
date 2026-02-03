import promptSync from 'prompt-sync'
const prompt = promptSync({ sigint: true })

function parseValue(value, field) {
  if (value === null || value.trim() === '') return null

  if (field.type === 'string') {
    if (field.pattern && !field.pattern.test(value)) return null
    return value
  }

  if (field.type === 'number') {
    const n = Number(value)
    return isNaN(n) ? null : n
  }

  if (field.type === 'int') {
    const n = Number(value)
    return Number.isInteger(n) ? n : null
  }

  return value
}

export default {
  input(text) {
    const value = prompt(text)
    if (value === null) process.exit()
    return value
  },

  inputValidated(label, field) {
    while (true) {
      const raw = prompt(`${label}: `)
      if (raw === null) process.exit()

      const parsed = parseValue(raw, field)

      if (parsed === null && field.required) {
        console.log('Invalid value, try again')
        continue
      }

      return parsed
    }
  },

  title: text => console.log(`\n${text}\n`),
  line: () => console.log('---------------------------')
}
