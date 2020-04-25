import React from 'react';
import register from "./img/job.svg";
import './style.css';
import axios from 'axios';
import Logo from "./img/logo_transparent.png";

export default class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      confirmedPassword: "",
      items: [],
      errorMessage: ''
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const data = {
      "confirmedPassword": this.state.confirmedPassword,
      "email": this.state.email,
      "password": this.state.password,
    };

    axios.post('http://localhost:8080/api/users', data)
      .then(response => {
        alert(response.data);
      })
      .catch(error => {
        if (error.response) {
          this.setState({errorMessage: error.response.data.message});
        } else {
          this.setState({errorMessage: "Nie można połączyć się z serwerem"});
        }
      });
  }

  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-md navbar-light sticky-top">
          <div className="container-fluid">
            <a className="navbar-brand" href="/">
              <img alt="Let's work" src={Logo} className="img-fluid"/>
            </a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
              <span
                className="navbar-toggler-icon">
              </span>
            </button>
            <div className="collapse navbar-collapse" id="navbarResposive">
              <ul className="navbar-nav ml-auto">
                <div className="btn-group">
                  <a className="btn bg-primary" href="/register">Załóż konto</a>
                  <a className="btn btn-primary" href="/login">Zaloguj się</a>
                </div>
              </ul>
            </div>
          </div>
        </nav>
        <div className="base-container text-center form-group">
          <div className="container register-form">
            <div className="form">
              <div className="image">
                <img src={register} alt="bg img"/>
              </div>
              <div className="form-content">
                <div className="row">
                  <div className="col-md-6">
                    <div className="form-group">
                      <label id="email"/>
                      <input
                        value={this.state.email}
                        name="email"
                        onChange={this.handleChange}
                        type="email"
                        className="form-control"
                        placeholder="Twój e-mail *"
                      />
                    </div>
                    <div className="form-group">
                      <div className="form-group">
                        <label id="confirmedPassword"/>
                        <input
                          value={this.state.confirmedPassword}
                          name="confirmedPassword"
                          onChange={this.handleChange}
                          type="password"
                          className="form-control"
                          placeholder="Potwierdzenie hasła *"
                        />
                        {this.getErrorMessage()}
                      </div>
                    </div>
                  </div>
                  <div className="col-md-6">
                    <label id="password"/>
                    <input
                      value={this.state.password}
                      name="password"
                      onChange={this.handleChange}
                      type="password"
                      className="form-control"
                      placeholder="Twoje hasło *"
                    />
                    <div className="form-group">
                      <button type="button" className="btnSubmit" onClick={this.handleSubmit}>Zarejestruj</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <footer>
          <div className="container-fluid padding">
            <div className="row text-center">
              <div className="col-md-1">
                <img src={Logo} alt="logo"/>
                <hr className="dark"/>
                <p>
                  777 777 777
                </p>
                <p>
                  joannnabiala@gmail.com
                </p>
              </div>
            </div>
            <div className="text-center">
              <div className="dark"/>
              <p>©lets-work-pl.pl</p>
            </div>
          </div>
        </footer>
      </div>
    );
  }

  getErrorMessage() {
    if (this.state.errorMessage) {
      return (
        <div className="alert alert-danger badge" role="alert">
          {this.state.errorMessage}
        </div>
      )
    }
    return null;
  }
}