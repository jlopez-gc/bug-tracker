import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import { Router } from '@reach/router';
import BugList from './components/BugList';
import BugEdit from './components/BugEdit';
import BugCreate from './components/BugCreate';

function App() {
    return (
        <div className="container">
            <Router>
                <BugList path="/" default />
                <BugCreate path="/bug" />
                <BugEdit path="/bug/:id" />
            </Router>
        </div>
    );
}

export default App;
