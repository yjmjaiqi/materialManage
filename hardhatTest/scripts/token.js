// 导入 Hardhat 库
const { ethers } = require("hardhat");
async function main() {
  // 获取合约工厂
  const BigToken = await ethers.getContractFactory("BigToken");

  // 部署合约
  console.log("部署 BigToken 合约...");
  bigToken = await BigToken.deploy();


  console.log(bigToken);

  console.log("BigToken 合约已成功部署到地址:", bigToken.target);
  const balance = await bigToken.getBalance("0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266");
  console.log("账户余额:", balance.toString());
}
  
// 执行部署脚本
main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
