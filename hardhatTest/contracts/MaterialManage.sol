// SPDX-License-Identifier: GPL-3.0

pragma solidity ^0.8.0;
contract MaterialManage{
    constructor(){}
    // 物资
    struct Material{
        string name; // 名称
        string specifications; // 规格
        string detail; // 详情
        string dateOfManufacture; // 生产日期
        string supplier; // 供应商
        string buyTime; // 购买时间
        string receiver; // 接收人
        string phone; // 联系电话
        string donationSite; // 始发地
        string destination; // 目的地
    }
    // 存储所有的材料
    mapping(bytes32 => Material) public materials;

    // 物资对于的运输站
    struct LogMaterial{
        string recorder; // 记录人名称
        string phone; // 联系电话
        string currentLocation; // 物资当前所在地
        string receiveTime; // 物资接收时间
    }
    // 存储物资的运输站
    mapping(bytes32 => LogMaterial[]) public logMaterials;

    // 生成结构体字段的哈希值
    function generateMaterialHash(
        string memory name,
        string memory specifications,
        string memory detail,
        string memory dateOfManufacture,
        string memory supplier,
        string memory buyTime,
        string memory receiver,
        string memory phone,
        string memory donationSite,
        string memory destination
    ) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(name, specifications, detail, dateOfManufacture, supplier, buyTime, receiver, phone,donationSite, destination));
    }

    // 添加新物资
    function addMaterial(
        string memory name,
        string memory specifications,
        string memory detail,
        string memory dateOfManufacture,
        string memory supplier,
        string memory buyTime,
        string memory receiver,
        string memory phone,
        string memory donationSite,
        string memory destination
    ) public returns (bytes32){
        bytes32 materialHash = generateMaterialHash(name, specifications, detail, dateOfManufacture, supplier, buyTime, receiver, phone,donationSite, destination);
        materials[materialHash] = Material(name, specifications, detail, dateOfManufacture, supplier, buyTime, receiver, phone,donationSite, destination);
        return materialHash;
    }

    // 通过交易哈希查看物资信息
    function getMaterialInfoByTransactionHash(bytes32 transactionHash) public view returns (Material memory) {
        return materials[transactionHash];
    }

    // 生成结构体字段的哈希值
    function generateLogMaterialHash(
        string memory recorder,
        string memory phone,
        string memory currentLocation,
        string memory receiveTime
    ) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(recorder, phone, currentLocation,receiveTime));
    }
    // 更新物资状态
    function addLogMaterial(
        string memory recorder,
        string memory phone,
        string memory currentLocation,
        string memory receiveTime,
        string memory materialHash
    ) public returns (bytes32){
        bytes32 result;
        assembly {
            result := mload(add(materialHash, 32))
        }
        // 获取已存在的数组
        LogMaterial[] storage logMaterialList = logMaterials[result];

        // 添加新的日志项到数组
        logMaterialList.push(LogMaterial(recorder, phone, currentLocation,receiveTime));

        // 更新映射中的数组
        logMaterials[result] = logMaterialList;

        return generateLogMaterialHash(recorder, phone, currentLocation,receiveTime);
    }
    // 通过交易哈希查看物资信息
    function getLogMaterialInfoByTransactionHash(string memory transactionHash) public view returns (LogMaterial[] memory) {
        bytes32 result;
        assembly {
            result := mload(add(transactionHash, 32))
        }
        return logMaterials[result];
    }

}