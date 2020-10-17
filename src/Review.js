import React, { Component } from "react";
import "./Review.css";
import editicon from "./asset/images/edit.svg";
import checkicon from "./asset/images/check.svg";

export default class Review extends Component {
  state = {
    isEdit: false,
    review: this.props.review,
  };

  handleOnChange = (e) => {
    this.setState({ review: e.target.value });
  };
  handleEdit = () => {
    this.setState({ isEdit: !this.state.isEdit });
  };
  render() {
    const { id, handleSubmit } = this.props;
    const { isEdit, review } = this.state;
    return (
      <div className="review-box">
        {isEdit ? (
          <input value={review} onChange={this.handleOnChange} />
        ) : (
            <span> {this.props.review} </span>
          )}
        <div className="icon-group">
          <img className="icon" onClick={this.handleEdit} src={editicon} />
          <img
            className="icon"
            onClick={() => {
              handleSubmit(review, id);
              this.setState({ isEdit: false });
            }}
            src={checkicon}
          />
        </div>
      </div>
    );
  }
}
