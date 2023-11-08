const { expect } = require("chai");
const { ethers} = require("hardhat");

describe("Token",function(){
    let Token,token,owner,addr1,addr2
    beforeEach(async() => {
        Token = await ethers.getContractFactory('BigToken');
        token = await Token.deploy();
        [owner,addr1,addr2] = await ethers.getSigners();
    })
    describe("test Deployment",() => {
        it("所有者正确",async() => {
            expect(await token.owner()).to.equal(owner.address);
        })
        it("部署者拥有所有通证",async() => {
            const totalSupply = await token.totalSupply();
            expect(await token.getBalance(owner.address)).to.equal(totalSupply);
        })
    })
    describe("发生Token",() => {
        it("正确发送Token",async() => {
            await token.transfer(addr1.address,100);
            let baAddr1 = await token.getBalance(addr1.address);
            // console.log(baAddr1 == 100);
            // console.log(addr1);
            expect(baAddr1).to.equal(99);
            // connect将addr1作为合约调用者msg.sender
            await token.connect(addr1).transfer(addr2.address,50);
            baAddr1 = await token.getBalance(addr1.address);
            expect(baAddr1).to.equal(49);
        })  
    })
})