import React from 'react';
import {
  Link,
  Redirect,
} from "react-router-dom";
import Logo from './img/logo_transparent.png'
import bg0 from './img/bg-6.jpg'
import bg1 from './img/bg-4.jpg'
import bg2 from './img/bg-5.jpg'
import axios from "axios";
import Moment from 'react-moment';
import 'moment-timezone';

const initialState = {
  categories: [],
  categoryName: "",
  title: "",
  content: "",
  notices: "",
  redirectToOtherUserProfile: false,
  message: "",
  titleError: "",
  contentError: "",
  name: "",

};

export default class MainPage extends React.Component {
  state = initialState;

  constructor(props) {
    super(props);
    this.state = {
      categories: [],
      categoryName: "",
      title: "",
      content: "",
      notices: "",
      authorEmail: "",
      redirectToOtherUserProfile: false,
      message: "",
      titleError: "",
      contentError: ""

    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleTitleChange = this.handleTitleChange.bind(this);
    this.handleContentChange = this.handleContentChange.bind(this);
    this.handleCategoryNameChange = this.handleCategoryNameChange.bind(this);
    this.fetchAdvertisements = this.fetchAdvertisements.bind(this);
    this.handlePostJobApplication = this.handlePostJobApplication.bind(this);
    this.handleJobApplicationChange = this.handleJobApplicationChange.bind(this);
    this.handleNewTitleChange = this.handleNewTitleChange.bind(this);
    this.handleNewContentChange = this.handleNewContentChange.bind(this);

  }

  handleNewTitleChange(event) {
    this.setState({
      "newTitle": event.target.value
    });
  }

  handleNewContentChange(event) {
    this.setState({
      "newContent": event.target.value
    });
  }


  handleJobApplicationChange(event) {
    this.setState({
      message: event.target.value
    });
  }

  handleTitleChange(event) {
    this.setState({
      "title": event.target.value
    });
  }

  handleContentChange(event) {
    this.setState({
      "content": event.target.value
    });
  }

  handleCategoryNameChange(event) {
    this.setState({
      categoryName: event.target.name
    });
  }

  handleSubmit(event) {
    const isValid = this.validate();
    if (isValid) {
      console.log(this.state);
      this.setState({initialState});
    }

    if (!this.state.categoryName.length) {
      alert("Ogłoszenie musi zawierac kategorie");
      return;
    }
    const data = {
      "categoryName": this.state.categoryName,
      "title": this.state.title,
      "content": this.state.content,
    };
    axios.post('http://localhost:8080/api/notices/add', data)
      .then(response => {
        alert("ogłoszenie zostało wysłane");
        window.location.reload(false);
      })
      .catch(error => {
        console.log(error);
      })


  }


  validate() {
    let titleError = "";
    let contentError = "";
    if (!this.state.content) {
      contentError = "Zawartość ogłoszenia nie może być pusta.";
    }


    if (!this.state.title) {
      titleError = "Tytuł ogłoszenia nie może być pusty.";
    }

    if (titleError || contentError) {
      this.setState({titleError, contentError});
      return false;
    }
    return true;
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories').then(response => {
      this.setState({categories: response.data});
    })
    this.fetchAdvertisements();

    axios.get('http://localhost:8080/api/auth').then(response => {
      console.log(response.data.name);
      this.setState({name: response.data.name});
    })
  }

  renderCategories() {
    const renderedCategories = [];

    for (let category of this.state.categories) {
      renderedCategories.push(this.makeCheckbox(category.id, category.name));
    }

    return (
      <ul>
        {renderedCategories}
      </ul>
    )
  }

  renderRadioButtonCategories() {
    const renderedCategories = [];

    for (let category of this.state.categories) {
      renderedCategories.push(this.makeRadioButton(category.id, category.name));
    }

    return (
      <ul>
        {renderedCategories}
      </ul>
    )
  }

  fetchAdvertisements() {
    const categoryUrl = this.state.categoryName ? this.state.categoryName + "/" : "";

    axios.get('http://localhost:8080/api/notices/find/' + categoryUrl).then(response => {
      this.setState({notices: response.data});
      console.log("this.state.categories")
    })
  }

  makeCheckbox(id, name) {
    const isSelected = this.state.categoryName === name;

    return (
      <div className="custom-control custom-checkbox custom-control-inline">
        <input type="checkbox"
               className="custom-control-input"
               checked={isSelected}
               onChange={this.handleCategoryNameChange}
               id={id}
               name={name}
        />
        <label className="custom-control-label" htmlFor={id}>{name}</label>
      </div>
    )
  }

  makeRadioButton(id, name) {
    const isSelected = this.state.categoryName === name;

    return (
      <div className="custom-control custom-control-inline">
        <input type="radio" className="form-check-input" checked={isSelected}
               onChange={this.handleCategoryNameChange} id={id} name={name}/>
        <label className="form-check-label  " htmlFor={id}>{name}</label>
      </div>
    )
  }

  renderAllNotices() {
    const renderedNotices = [];
    for (let notice of this.state.notices) {
      renderedNotices.push(this.renderNotice(notice));
    }
    return (
      <div className="row">
        {renderedNotices}
      </div>
    )
  }


  deleteAdvertisement(notice) {
    axios.delete("http://localhost:8080/api/notices/delete/" + notice.id).then(response => {
      this.fetchAdvertisements()
    })
  }

  updateAdvertisement(notice) {
    const data = {
      "newContent": this.state.newContent,
      "newTitle": this.state.newTitle
    }
    axios.put("http://localhost:8080/api/notices/update/" + notice.id, data)
      .then(response => {
        window.location.reload(false);
      })

  }

  renderNotice(notice) {
    const modalId = "exampleModal" + notice.id;
    const dataTarget = "#" + modalId;
    return (
      <li className="timeline-item bg-white rounded col-md-12 p-4 shadow">
        <div className="timeline-arrow"/>
        <div className="row">
          <div className=" col-md-11">
            <h2 className="h5 mb-0">
              {notice.title}
            </h2>
          </div>
          {notice.authorEmail === this.state.name ?
            <div className="dropleft text-right pull-right">
              <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              </button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenu2">
                <button
                  className="dropdown-item"
                  type="button"
                  data-toggle="modal"
                  onClick={() => {
                    this.setState({newTitle: notice.title, newContent: notice.content})
                  }}
                  data-target={dataTarget}>Edytuj
                </button>
                <button className="dropdown-item" type="button" onClick={() => this.deleteAdvertisement(notice)}>Usuń
                </button>
              </div>
            </div> : null}
        </div>

        <span className="badge badge-info">
          {notice.categoryName}
        </span>
        <span className="small text-gray">
          <i className="fa fa-clock-o mr-1"/>
           <Moment format="YYYY/MM/DD">
               {notice.date}
            </Moment>
        </span>
        <p>
          Dodano przez:
          <span className="badge badge-pill badge-light">
            {notice.authorEmail}
            </span>
        </p>
        <p className="text-small mt-2 font-weight-light">
          {notice.content}
        </p>
        <div className="text-right mb-3">
          <a className="btn btn-success" data-toggle="modal" data-target={dataTarget} data-whatever="@mdo">Aplikuj!</a>
        </div>


        <div className="modal fade bd-example-modal-lg" id={modalId} tabIndex="-1" role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
          <div className="modal-dialog modal-lg" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="exampleModalLabel">Edytuj swoje ogłoszenie</h5>
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <div className="custom-control custom-checkbox">
                  <div className="form-group">
                    <label htmlFor="title" className="col-form-label">Tytuł ogłoszenia:</label>
                    <input
                      type="text"
                      className="form-control"
                      id="recipient-name"
                      value={this.state.newTitle}
                      onChange={this.handleNewTitleChange}
                    />

                    <div style={{color: 'red'}}>
                      {this.state.titleError}
                    </div>
                  </div>
                  <div className="form-group">
                    <label htmlFor="content" className="col-form-label">Treść ogłoszenia:</label>
                    <textarea
                      className="form-control"
                      id="content"
                      value={this.state.newContent}
                      onChange={this.handleNewContentChange}
                    />
                    <div style={{color: 'red'}}>
                      {this.state.contentError}
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                <button type="button" className="btn btn-primary"
                        onClick={() => this.updateAdvertisement(notice)}>Zapisz i dodaj
                </button>
              </div>
            </div>
          </div>
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
                    <label type="text" className="form-control" id="recipient-name">{notice.authorEmail}</label>
                  </div>
                  <div className="form-group">
                    <label htmlFor="message-text" className="col-form-label">Treść:</label>
                    <textarea className="form-control" id="message-text" value={this.state.message}
                              onChange={this.handleJobApplicationChange}/>
                  </div>
                </form>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                <button type="button" className="btn btn-primary"
                        onClick={() => this.handlePostJobApplication(notice)}>Zapisz i wyślij!
                </button>
              </div>
            </div>
          </div>
        </div>
      </li>
    )
  }

  handlePostJobApplication(notice) {
    const data = {
      "message": this.state.message,
      "advertisementId": notice.id,
    };
    let result = axios.post("http://localhost:8080/api/job-applications", data)
    result.then(response => {
      console.log(response)
      alert("Twoja aplikacja została wysłana!");
      window.location.reload(false);
    })
    result.catch(error => {
      console.log(error);
    })
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
                  <a className="btn bg-primary" href="/userpanel">Moje konto</a>
                  <a className="btn btn-primary"
                     data-toggle="modal"
                     data-target="#addNoticeId">
                    + Dodaj ogłoszenie
                  </a>
                  <a className="btn btn-primary" href="/logoutsite">Wyloguj</a>
                </div>
              </ul>
            </div>
          </div>
        </nav>
        <nav id="slides" className="carousel slide" data-ride="carousel">
          <ul className="carousel-indicators">
            <li data-target="#slides" data-slide-to="0" className="active"/>
            <li data-target="#slides" data-slide-to="1"/>
            <li data-target="#slides" data-slide-to="2"/>
          </ul>
          <div className="carousel-inner">
            <div className="carousel-item active">
              <img alt="bg 0" src={bg1} className="img-fluid"/>
              <div className="carousel-caption">
                <h1 className="display-2">Let's work</h1>
                <h3> Znajdź pracę swoich marzeń</h3>
              </div>
            </div>
            <div className="carousel-item">
              <img alt="bg-3" src={bg0} className="img-fluid"/>
            </div>
            <div className="carousel-item">
              <img alt="bg-5" src={bg2} className="img-fluid"/>
            </div>
          </div>
        </nav>
        <nav className="container">
          <div className="row jumbotron">
            <div>
              <h5>Wybierz kategorię ogłoszeń:</h5>
            </div>


            <div className="col-md-12">
              {this.renderRadioButtonCategories()}
              <button
                className="btn-primary"
                onClick={this.fetchAdvertisements}
              >
                szukaj
              </button>
            </div>
            <div className="col-md-11 mx-auto">
              <ul className="timeline">
                {this.renderAllNotices()}
              </ul>
            </div>
          </div>
        </nav>
        <nav className="container-fluid padding">
          <div className="row welcome text-center">
            <div className="col-12">
              <h1 className="display-4">
                <p> Znajdź ją z nami...</p>
              </h1>
            </div>
          </div>
        </nav>
        <nav className="container-fluid padding">
          <div className="row text-center padding">
            <div className="col-xs-12 col-sm-6 col-md-4 col-xl-3">
              <i className="fas fa-search fa-7x"/>
              <h3>Znajdź</h3>
              <p>Stosując filtry znajdź interesującą Cię ofertę</p>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-3">
              <i className="fas fa-envelope-square fa-7x"/>
              <h3>Skontaktuj się</h3>
              <p>Napisz do pracodawcy</p>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-3">
              <i className="far fa-handshake fa-7x"/>
              <h3>Przejdź rekrutację</h3>
              <p>Umów się na rozmowę</p>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-3">
              <i className="far fa-smile fa-7x"/>
              <h3>Pracuj!</h3>
              <p>Miej pracę o jakiej zawsze marzyłeś</p>
            </div>
          </div>
          <hr className="my-4">
          </hr>
          {/*dodawanie ogłoszeń panel modal*/}
          <div className="modal fade bd-example-modal-lg" id="addNoticeId" tabIndex="-1" role="dialog"
               aria-labelledby="exampleModalLabel"
               aria-hidden="true">
            <div className="modal-dialog modal-lg" role="document">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="exampleModalLabel">Dodaj ogłoszenie</h5>
                  <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div className="modal-body">
                  <div className="custom-control custom-checkbox">
                    <p>Wybierz kategorię ogłoszenia:</p>

                    <container>
                      <div className="row">

                        {this.renderCategories()}

                      </div>
                    </container>
                    <div className="form-group">
                      <label htmlFor="title" className="col-form-label">Tytuł ogłoszenia:</label>
                      <input
                        type="text"
                        className="form-control"
                        id="recipient-name"
                        value={this.state.title}
                        onChange={this.handleTitleChange}
                      />

                      <div style={{color: 'red'}}>
                        {this.state.titleError}
                      </div>
                    </div>
                    <div className="form-group">
                      <label htmlFor="content" className="col-form-label">Treść ogłoszenia:</label>
                      <textarea
                        className="form-control"
                        id="content"
                        value={this.state.content}
                        onChange={this.handleContentChange}
                      />
                      <div style={{color: 'red'}}>
                        {this.state.contentError}
                      </div>
                    </div>
                  </div>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                  <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Zapisz i dodaj</button>
                </div>
              </div>
            </div>
          </div>
        </nav>
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
              <p>
                ©lets-work-pl.pl
              </p>
            </div>
          </div>
        </footer>
      </div>
    );
  }
}