import { createHistory, createMemorySource, LocationProvider, Router } from '@reach/router';
import { render } from '@testing-library/react';
import React from 'react';

export const renderWithRouter = (
    component: JSX.Element,
    { route = '/', history = createHistory(createMemorySource(route)) } = {},
) => {
    return {
        ...render(<LocationProvider history={history}>{component}</LocationProvider>),
        history,
    };
};

export const renderWithRouterWrapper = (
    ui: JSX.Element,
    { route = '/', history = createHistory(createMemorySource(route)) } = {},
) => {
    return {
        ...render(
            <LocationProvider history={history}>
                <Router>{ui}</Router>
            </LocationProvider>,
        ),
        history,
    };
};
