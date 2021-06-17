import React from 'react';
import './App.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import BugList from './components/BugList';

function App() {
    return (
        <div className="App">
            <BugList />
        </div>
    );
}

export default App;
