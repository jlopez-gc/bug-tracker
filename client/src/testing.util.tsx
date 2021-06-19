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
    component: JSX.Element,
    { route = '/', history = createHistory(createMemorySource(route)) } = {},
) => {
    return {
        ...render(
            <LocationProvider history={history}>
                <Router>{component}</Router>
            </LocationProvider>,
        ),
        history,
    };
};
