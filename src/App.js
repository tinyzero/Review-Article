import React, { Component } from "react";
import Review from "./Review";
import "./App.css";

const response = {
  data: [
    { id: 1, review: "น่าอร่อยจัง" },
    {
      id: 2,
      review: `เมนูขึ้นชื่อของร้านมัลลิการ์ ก็ต้องเย็นตาโฟ ทั้งแบบใจเสาะ (เผ็ดปานกลาง) และรสเจ็บ (เผ็ดจริงจัง) แต่กว่าจะรู้สึกเผ็ดผ่านไปครึ่งชาม 555 รสเข้มข้น ไม่ต้องปรุงเลย เส้นใหญ่เหนียวนุ่ม ทานกับเต้าหู้ทอด หอม กรอบนอกนุ่มในอร่อยมากค่ะ"
      》 มาธุระแถวพืชสวนโลก เกินเวลาทานมื้อเที่ยง เจอร้านไหนก็แวะ เปิดแอพวงในหาร้านที่ใกล้ๆ เจอร้านข้าวซอยชื่อคุ้นเคย มาเปิดละแวกนี้ เลยเลี้ยวเข้ามาชิม
        》 ภานในร้านมี ที่นั่งประมาณ 15โต๊ะ โดยประมาณ ภายในร้านแลดูวุ่นวายดี มีคนเข้ามาทานตลอด มีที่จอดรถเยอะ วันนี้มาคนเดียวเลยลองชิมอาหารดังนี้`,
    },
    {
      id: 3,
      review: `เปิดแอพวงในหาร้านที่ใกล้ๆ เจอร้านข้าวซอยชื่อคุ้นเคย มาเปิดละแวกนี้ เลยเลี้ยวเข้ามาชิม
        》 ภานในร้านมี ที่นั่งประมาณ 15โต๊ะ โดยประมาณ ภายในร้านแลดูวุ่นวายดี มีคนเข้ามาทานตลอด มีที่จอดรถเยอะ วันนี้มาคนเดียวเลยลองชิมอาหารดังนี้`,
    },
  ],
};
class Post extends Component {
  state = {
    search: "",
    data: [],
    type: null,
  };

  handleChange = (e) => {
    this.setState({ search: e.target.value });
  };

  handleTypeOnChange = (e) => {
    this.setState({ type: e.target.value });
  };

  handleSubmit = (review, id) => {
    const result = response.data.map((item) => {
      if (item.id === id) {
        item.review = review;
      }
      return item;
    });
    this.setState({ data: result });
    // request patch or put to edit data here
  };

  todo = (search, type) => {
    //fetch get api ( search, type )
    this.setState({ data: response.data });
  };

  handleClick = (e) => {
    const { search, type } = this.state;
    this.todo(search, type);
  };

  render() {
    const { title } = this.props;
    const { data } = this.state;
    console.log(" data ja ", data);
    return (
      <div className="App">
        <header className="App-header">
          <h1> {title} </h1>
          <div className="container">
            <input
              className="search-box"
              type="text"
              placeholder="Search something 🍓"
              onChange={this.handleChange}
            />
            <div className="search-group">
              <input
                type="radio"
                id="foodid"
                name="search-type"
                value="id"
                onChange={this.handleTypeOnChange}
              />
              <label htmlFor="foodid"> ID </label>
              <input
                type="radio"
                id="menu"
                name="search-type"
                value="menu"
                onChange={this.handleTypeOnChange}
              />
              <label htmlFor="menu">Menu</label>
            </div>
            <button className="btn-search" onClick={this.handleClick}>
              Search
            </button>
          </div>
          <div className="output-box">
            {typeof data == "object" &&
              data.map((item) => {
                return (
                  <Review
                    id={item.id}
                    review={item.review}
                    handleSubmit={this.handleSubmit}
                  />
                );
              })}
          </div>
        </header>
      </div>
    );
  }
}

export default Post;
