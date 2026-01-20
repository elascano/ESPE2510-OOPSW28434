const { connectDB } = require("./db");
const Tool = require("./toolModel"); 

class ToolRepository {
  async create(toolData) {
    await connectDB();
    return await new Tool(toolData).save();
  }

  async findAll() {
    await connectDB();
    return await Tool.find().sort({ createdAt: -1 }).lean();
  }

  async findByBusinessId(toolId) {
    await connectDB();
    return await Tool.findOne({ id: toolId }).lean();
  }

  async updateByBusinessId(toolId, toolData) {
    await connectDB();
    return await Tool.findOneAndUpdate({ id: toolId }, toolData, {
      new: true,
      runValidators: true
    }).lean();
  }

  async deleteByBusinessId(toolId) {
    await connectDB();
    return await Tool.findOneAndDelete({ id: toolId }).lean();
  }
}

module.exports = ToolRepository;