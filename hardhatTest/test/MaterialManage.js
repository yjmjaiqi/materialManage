const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("MaterialManage Contract", function () {
  let materialManage;
  let owner; // undefined
  let user; // undefined

  before(async () => {
    [owner, user] = await ethers.getSigners();

    const MaterialManage = await ethers.getContractFactory("MaterialManage");
    materialManage = await MaterialManage.deploy();
  });

  it("Should add and retrieve material information(添加物资信息)", async function () {
    const materialHash = await materialManage.addMaterial(
      "MaterialName",
      "Specs",
      "Details",
      "2023-01-01",
      "Supplier",
      "2023-02-01",
      "Receiver",
      "1234567890",
      "DonationSite",
      "Destination"
    );
  });

  it("Obtaining Material Information(获取物资信息)", async function () {
    await materialManage.addMaterial(
      "MaterialName",
      "Specs",
      "Details",
      "2023-01-01",
      "Supplier",
      "2023-02-01",
      "Receiver",
      "1234567890",
      "DonationSite",
      "Destination"
    );
    const materialHash = await materialManage.generateMaterialHash(
        "MaterialName",
        "Specs",
        "Details",
        "2023-01-01",
        "Supplier",
        "2023-02-01",
        "Receiver",
        "1234567890",
        "DonationSite",
        "Destination"
      )
      const materialInfo = await materialManage.getMaterialInfoByTransactionHash(materialHash);
  
      expect(materialInfo.name).to.equal("MaterialName");
      expect(materialInfo.specifications).to.equal("Specs");
      expect(materialInfo.detail).to.equal("Details");
      expect(materialInfo.dateOfManufacture).to.equal("2023-01-01");
      expect(materialInfo.supplier).to.equal("Supplier");
      expect(materialInfo.buyTime).to.equal("2023-02-01");
      expect(materialInfo.receiver).to.equal("Receiver");
      expect(materialInfo.phone).to.equal("1234567890");
      expect(materialInfo.donationSite).to.equal("DonationSite");
      expect(materialInfo.destination).to.equal("Destination");
      console.log(materialInfo.name);
      console.log(materialInfo.specifications);
      console.log(materialInfo.detail);
      console.log(materialInfo.dateOfManufacture);
      console.log(materialInfo.supplier);
      console.log(materialInfo.buyTime);
      console.log(materialInfo.receiver);
      console.log(materialInfo.phone);
      console.log(materialInfo.donationSite);
      console.log(materialInfo.destination);
  });
  it("Add and Obtain information on material transportation station records(添加并获取物资运输站点记录信息)", async function () {
    const materialHash = await materialManage.addMaterial(
      "MaterialName",
      "Specs",
      "Details",
      "2023-01-01",
      "Supplier",
      "2023-02-01",
      "Receiver",
      "1234567890",
      "DonationSite",
      "Destination"
    );
    
    await materialManage.addLogMaterial(
      "RecorderName",
      "9876543210",
      "CurrentLocation",
      "2023-03-01",
      materialHash
    );

    const logMaterialInfo = await materialManage.getLogMaterialInfoByTransactionHash(materialHash);

    expect(logMaterialInfo[0].recorder).to.equal("RecorderName");
    expect(logMaterialInfo[0].phone).to.equal("9876543210");
    expect(logMaterialInfo[0].currentLocation).to.equal("CurrentLocation");
    expect(logMaterialInfo[0].receiveTime).to.equal("2023-03-01");
    console.log(logMaterialInfo[0].recorder);
    console.log(logMaterialInfo[0].phone);
    console.log(logMaterialInfo[0].currentLocation);
    console.log(logMaterialInfo[0].receiveTime);
  });
});
