1. npm init -y 初始化一个项目
2. npm install --save-dev hardhat 安装
3. npx hardhat  局部安装命令
4. npm install --save-dev "hardhat@^2.17.2" "@nomicfoundation/hardhat-toolbox@^3.0.0"
5. npx hardhat compile 编译
6. npx hardhat test test/Token.js 测试
7. npx hardhat run .\scripts\token.js 部署
8. npx hardhat run .\scripts\token.js --network ganache部署到gnache上 ,先启动ganache服务 
9. 使用hardhat主网fork测试并且解锁账户
9.1  https://goto.etherscan.com/ 进入官网
9.2  Dai Stablecoin (DAI)  搜索这个币
9.3  选择一个不是合约的账户  0x1B7BAa734C00298b9429b518D621753Bb0f6efF2
9.4  写测试脚本Unlock.js
9.5 hardhat:{
      forking:{
        url: 'https://eth-mainnet.g.alchemy.com/v2/qRgVj4rltKyNJypp7RyG_tjIo0ufr1WG',
      }
    }写配置文件

10. 将钱包或Dapp连接到安全帽网络
10.1 npx hardhat node 启动服务 
10.2 npx hardhat run scripts/deploy.js --network localhost 新开一个端口执行命令
11. 扁平化合约 npx hardhat flatten contracts/Foo.sol