import React from 'react';
import Logo from "./img/logo_transparent.png";
import axios from 'axios';
import {Link} from "react-router-dom";


export default class OtherUserProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/auth').then(response => {
      console.log(response.data.name);
      this.setState({name: response.data.name});
    })
  }

  offerItem(id, title, categoryName, date, authorEmail, content) {
    const modalId = "exampleModal" + id;
    const dataTarget = "#" + modalId;
    return (
      <li className="timeline-item bg-white rounded ml-3 p-4 shadow">
        <div className="timeline-arrow"/>
        <h2 className="h5 mb-0">
          {title}
        </h2>
        <span className="badge badge-info">
          {categoryName}
        </span>
        <span className="small text-gray">
          <i className="fa fa-clock-o mr-1"/>
          {date}
        </span>
        <p>
          Dodano przez: <span className="badge badge-pill badge-light">{authorEmail}</span>
        </p>
        <p className="text-small mt-2 font-weight-light">
          {content}
        </p>
        <div className="text-right mb-3">
          <a className="btn btn-success" data-toggle="modal" data-target={dataTarget} data-whatever="@mdo">Aplikuj!</a>
        </div>
        <div className="modal fade" id={modalId} tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="exampleModalLabel">Stwórz swoje zgłoszenie </h5>
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <form>
                  <div className="form-group">
                    <label htmlFor="recipient-name" className="col-form-label">Pracodawca:</label>
                    <label type="text" className="form-control" id="recipient-name">{authorEmail}</label>
                  </div>
                  <div className="form-group">
                    <label htmlFor="message-text" className="col-form-label">Treść:</label>
                    <textarea className="form-control" id="message-text"/>
                  </div>
                </form>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                <button type="button" className="btn btn-primary">Zapisz i wyślij!</button>
              </div>
            </div>
          </div>
        </div>
      </li>
    )
  }

  render() {
    return (
      <div className="layout">
        <nav className="navbar navbar-expand-md navbar-light sticky-top">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              <img alt="Let's work" src={Logo} className="img-fluid"/>
            </a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span className="navbar-toggler-icon">
            </span>
            </button>
            <div className="collapse navbar-collapse" id="navbarResposive">
              <ul className="navbar-nav ml-auto">
                <div className="btn-group">
                  <Link to="/logout" className="btn bg-primary">Wyloguj się</Link>
                </div>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container">
          <div className="row profile">
            <div className="col-md-3">
              <div className="profile-sidebar">
                <div className="profile-userpic">
                  <img
                    className="img-responsive" alt=""/>
                </div>
                <div className="profile-usertitle">
                  <div className="profile-usertitle-name">
                    {this.state.name}
                  </div>
                </div>
                <div className="profile-usermenu">
                  <ul className="nav">
                    <li className="active">
                      <a href="#">
                        <i className="glyphicon glyphicon-home"/>
                        Przegląd </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-md-9">
              <div className="profile-content">
                <ul className="timeline">
                  {this.offerItem("1", "Tytył ogłoszenia nr 1", "Frontend", "14.04.2020", "marian@marian.pl", "treść ogłosznia dla kategorii Frontend" +
                    " treść ogłosznia dla kategorii Frontend treść ogłosznia dla kategorii Frontend treść ogłosznia dla kategorii Frontend" +
                    "treść ogłosznia dla kategorii Frontendtreść ogłosznia dla kategorii Frontendtreść ogłosznia dla kategorii Frontend" +
                    "treść ogłosznia dla kategorii Frontend treść ogłosznia dla kategorii Frontend")}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}