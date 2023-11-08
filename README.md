基于区块链的物资管理系统

前端技术 VUE+elemntPlus+axios+router+echarts+web3j(调用合约)

后端技术 Java,springboot+mybatisplus+springsecurity+redis。注意修改yml配置文件，填写自己邮箱授权码和邮箱地址，修改数据库，redis配置

合约测试 npm init -y 初始化一个项目 npx hardhat compile 编译 npx hardhat test test/MaterialManage.js 测试 npx hardhat run .\scripts\materialManage.js 部署

数据库 Mysql8

区块链技术 solidity、hardhat、本地私链，metamask钱包

项目描述：

1、利用区块链技术解决传统物资的信任、溯源问题，用区块链技术记录物资信息，防止信息篡改，可溯源，解决捐赠者与受助人间的信任问题

2、区块链与MySQL数据双存储，智能合约主要存储物资信息，而MySQL不仅存储物资信息还存储用户相关信息，并将交易哈希记录起来，用户可以在页面通过查看物资详情， 知道物资从哪来，谁发送的，并且可以通过哈希进行物资信息溯源验证物资是否上链

3、角色分为管理员与普通用户，管理员管理用户行为，审核物资，审核用户请求删除未发放的物资信息，并且记录物资运输状态，普通用户模拟物资的发放与物资溯源

项目主要是将物资信息上链，解决物资溯源问题，简单使用普通用户与管理员两个角色模拟物资发放审核上链溯源
项目地址：
    https://github.com/yjmjaiqi/materialManage.git   master分支
