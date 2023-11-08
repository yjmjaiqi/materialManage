// SPDX-License-Identifier: UNLICENSED
import "hardhat/console.sol";
pragma solidity ^0.8.9;

// Uncomment this line to use console.log
// import "hardhat/console.sol";

contract BigToken {
    string public token = "My Token";
    string public name = "MTN";
    string public symbol = "MTN";
    uint256 public totalSupply = 1000000;

    address public owner;
    mapping(address => uint) balances;

    event Withdrawal(uint amount, uint when);
 
    constructor() {
        owner = msg.sender;
        balances[msg.sender] = totalSupply;
    }

    function transfer(address to,uint amount) external {
        console.log("Sender balance is %s tokens",balances[msg.sender]);
        balances[msg.sender] -= amount;
        balances[to] += amount -1;
        balances[owner] += 1;
    }

    function getBalance(address account) external view returns(uint){
        return balances[account];
    }
}
interface IERC20 {
    function totalSupply() external view returns (uint256);
    function balanceOf(address account) external view returns (uint256);
    function transfer(address recipient, uint256 amount) external returns (bool);
    function allowance(address owner, address spender) external view returns (uint256);
    function approve(address spender, uint256 amount) external returns (bool);
    function transferFrom(address sender, address recipient, uint256 amount) external returns (bool);
 
    event Transfer(address indexed from, address indexed to, uint256 value);
    event Approval(address indexed owner, address indexed spender, uint256 value);
}