// 导入 Hardhat 库
const { ethers } = require("hardhat");
async function main() {
  // 获取合约工厂
  const Material = await ethers.getContractFactory("MaterialManage");

  // 部署合约
  console.log("部署 MaterialManage 合约...");
  material = await Material.deploy();


//   console.log(material);

  console.log("material 合约runner:", material.runner);
  console.log("material 合约已成功部署到地址:", material.target);
  const Hash = await material.generateLogMaterialHash("1","1","1","2023-11-03");
  console.log("Hash:", Hash);
}
  
// 执行部署脚本
main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
