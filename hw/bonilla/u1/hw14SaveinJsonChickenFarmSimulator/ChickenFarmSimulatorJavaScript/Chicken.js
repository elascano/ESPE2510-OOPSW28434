class Chicken {
  constructor(name, color, age, isMolting, id) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  toString() {
    return `Chicken { id=${this.id}, name=${this.name}, color=${this.color}, age=${this.age}, isMolting=${this.isMolting} }`;
  }
}

module.exports = { Chicken };