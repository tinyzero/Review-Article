import React, { Component } from "react";
import Review from "./Review";
import "./App.css";

import axios from 'axios';

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

  handleSubmit = async (review, id, original) => {
    const { data } = this.state
    const result = data.map((item) => {
      if (item.id === id) {
        item.review = review;
      }
      return item;
    });

    this.setState({ data: result });

    try {
      const url = `http://docker.for.mac.localhost:8080/reviews/${id}`
      let bodyFormData = new FormData();
      bodyFormData.append('new_review', review);
      bodyFormData.append('original_review', original);
      const response = await axios({ method: 'PUT', url, data: bodyFormData });
      this.setState({ data: response.data.payload });

    } catch (error) {
      console.log(error);
    }
  };

  search = async (search, type) => {
    if (type === 'id') {
      const url = `http://docker.for.mac.localhost:8080/reviews/${search}`
      try {
        const response = await axios({ method: 'GET', url });
        this.setState({ data: response.data.payload });

      } catch (error) {
        console.log(error);
      }
    } else {
      const url = `http://docker.for.mac.localhost/reviews?query=${search}`
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
          {/* <h1> {title} </h1> */}
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
