import promptSync from 'prompt-sync'

const prompt = promptSync()

export default {
  input: text => prompt(text),
  title: text => console.log(`\n${text}\n`),
  line: () => console.log('---------------------------')
}
