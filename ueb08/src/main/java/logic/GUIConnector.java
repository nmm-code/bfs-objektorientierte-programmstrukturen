package logic;

    /**
     * Connection from the logic to the gui.
     */
    public interface GUIConnector {
        /**
         * Will display the math problem on the gui.
         *
         * @param problem the problem to be displayed
         */
        void displayProblem(MathProblem problem);

        /**
         * Changes the gui after a problem has been solved. Will change the progress display, as well as add the just solved
         * assignment to the overview of the previous assignments.
         *
         * @param correctSolution the correct solution for the current problem
         * @param givenSolution   the solution the user has given for the current problem
         * @param solvedCorrectly if the user has solved the current problem correctly. This could be interfered from the
         *                        two other parameters, but to keep all logical operations out of the gui classes, this
         *                        information is given.
         */
        void displaySolved(int correctSolution, int givenSolution, boolean solvedCorrectly);

        /**
         * Called when the game has ended. After this method has been called, it should not be possible for the user to
         * input anything, as it might lead to nonsensical behaviour.
         *
         * @param correctlySolved the number of problems that were solved correctly
         */
        void gameEnded(int correctlySolved);
}
