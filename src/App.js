import React, { Component } from "react";
import Review from "./Review";
import "./App.css";

import axios from 'axios';

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
    search: '',
    data: [],
    type: 'keyword',
  };

  handleTextChange = (e) => {
    this.setState({ search: e.target.value });
  };

  switchSearchType = (e) => {
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

  search = async (search, type) => {
    if (type === 'id') {
      let url = `http://localhost:8080/reviews/${search}`
      try {
        const response = await axios({ method: 'GET', url });
        this.setState({ data: response.data.payload });

      } catch (error) {
        console.log(error);
      }
    } else {
      let url = `http://localhost:8080/reviews?query=${search}`
      try {
        const response = await axios({ method: 'GET', url });
        this.setState({ data: response.data.payload });

      } catch (error) {
        console.log(error);
      }
    }
  };

  handleClick = (e) => {
    const { search, type } = this.state;
    this.search(search, type);
  };

  render() {
    const { title } = this.props;
    const { data } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <h1> {title} </h1>
          <div className="container">
            <input
              className="search-box"
              type="text"
              placeholder="Search something 🍓"
              onChange={this.handleTextChange}
            />
            <div className="search-group">
              <input
                type="radio"
                id="foodid"
                name="search-type"
                value="id"
                onChange={this.switchSearchType}
              />
              <label htmlFor="foodid"> by ID </label>
              <input
                type="radio"
                id="menu"
                name="search-type"
                value="keyword"
                onChange={this.switchSearchType}
              />
              <label htmlFor="menu">by keyword</label>
            </div>
            <button className="btn-search" onClick={this.handleClick}>
              Search
            </button>
          </div>
          <div className="output-box">
            {data?.map((item) => {
              console.log(item)
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
